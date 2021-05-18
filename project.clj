(defproject com.rentpath/clj-soap "0.2.0-SNAPSHOT"
  :description "SOAP Client and Server using Apache Axis2."
  :url "https://github.com/rentpath/clj-soap"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"confluent" {:url           "https://packages.confluent.io/maven/"}
                 "releases"  {:url           "https://nexus.tools.rentpath.com/repository/maven-releases/"
                              :sign-releases false
                              :username      [:gpg :env/nexus_username]
                              :password      [:gpg :env/nexus_password]}
                 "snapshots" {:url           "https://nexus.tools.rentpath.com/repository/maven-snapshots/"
                              :sign-releases false
                              :username      [:gpg :env/nexus_username]
                              :password      [:gpg :env/nexus_password]}}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/data.xml "0.0.8"]
                 [clj-time "0.12.0"]
                 [org.apache.axis2/axis2-adb "1.7.4"]
                 [org.apache.axis2/axis2-transport-http "1.7.4"]
                 [org.apache.axis2/axis2-transport-local "1.7.4"]
                 [org.apache.axis2/axis2-jaxws "1.7.4"]]
  :aot [clj-soap.server-test]
  :profiles {:dev {:jvm-opts     ["-Dapplication.environment=dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]]}
             :test {:jvm-opts ["-Dapplication.environment=test"]}})

