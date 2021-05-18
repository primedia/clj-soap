(ns clj-soap.server
  (:import [org.apache.axis2.engine AxisServer]))

;;; Defining SOAP Server

(defn flatten1 [coll] (mapcat identity coll))

(defn gen-class-method-decls [method-defs]
  (flatten1
    (letfn [(gen-decl [method-name arglist body]
              [method-name
               (vec (for [arg arglist] (or (:tag (meta arg)) String)))
               (or (:tag (meta arglist)) 'void)])]
      (for [method-def method-defs]
        (cond
          (vector? (second method-def))
          (list (let [[method-name arglist & body] method-def]
                  (gen-decl method-name arglist body)))
          (seq? (second method-def))
          (let [[method-name & deflist] method-def]
            (for [[arglist & body] deflist]
              (gen-decl method-name arglist body))))))))

(defn gen-method-defs [prefix method-defs]
  (flatten1
    (for [method-def method-defs]
      (cond
        (vector? (second method-def))
        (list (let [[method-name arglist & body] method-def]
                `(defn ~(symbol (str prefix method-name))
                   ~(vec (cons 'this arglist)) ~@body)))
        (seq? (second method-def))
        (let [[method-name & deflist] method-def]
          (cons
            `(defmulti ~(symbol (str prefix method-name))
               (fn [~'this & args#] (vec (map class args#))))
            (for [[arglist & body] deflist]
              `(defmethod ~(symbol (str prefix method-name))
                 ~(vec (map #(:tag (meta %)) arglist))
                 ~(vec (cons 'this arglist)) ~@body))))))))

(defmacro defservice
  "Define SOAP class.
  i.e. (defsoap some.package.KlassName (myfunc [String a int b] String (str a (* b b))))"
  [class-name & method-defs]
  (let [prefix (str (gensym "prefix"))]
    `(do
       (gen-class
         :name ~class-name
         :prefix ~prefix
         :methods ~(vec (gen-class-method-decls method-defs)))
       ~@(gen-method-defs prefix method-defs))))

(defn serve
  "Start SOAP server.
  argument classes is list of strings of classnames."
  [& classes]
  (let [server (AxisServer.)]
    (doseq [c classes]
      (.deployService server (str c)))))
