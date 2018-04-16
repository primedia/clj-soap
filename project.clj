(defproject com.rentpath/clj-soap "see :git-version"
  :description "SOAP Client and Server using Apache Axis2."
  :url "https://github.com/primedia/clj-soap"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo/"
                                     :username [:gpg :env/CLOJARS_USERNAME]
                                     :password [:gpg :env/CLOJARS_PASSWORD]
                                     :sign-releases false}]]
  :dependencies [[org.apache.axis2/axis2-adb             "1.6.2"]
                 [org.apache.axis2/axis2-transport-http  "1.6.2"]
                 [org.apache.axis2/axis2-transport-local "1.6.2"]]
  :plugins [[org.clojars.cvillecsteele/lein-git-version "1.2.7"]]
  :git-version {:version-cmd "./script/version"
                :path "src/clj_soap"
                :root-ns "clj-soap.core"}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :test {:aot [clj-soap.test.core]}})
