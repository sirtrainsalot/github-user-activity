(ns github-user-activity.fun_core_test_runner
  (:require [github-user-activity.fun_core :refer [get-username]]))

;; hjelpemetoder

(defn print-test-title [testname]
  (println "")
  (println "----------" testname "----------")
  (println ""))

(defn print-successful-test [fun-name]
  (println (format "✅ - \"%s\" evaluerer korrekt:" fun-name)))

(defn print-failed-test [fun-name actual-content expected-content]
  (println (format "❌ - evalueringen av \"%s\" har feilet:" fun-name))
  (println (format "     -> actual:   %s" actual-content))
  (println (format "     -> expected: %s" expected-content)))


(defn check-content-println
  "Sjekk at fornvented tekstlig output er korrekt"
  [fun-name actual-function expected-result]
  (let [actual (with-out-str (actual-function))
        expected (str expected-result "\n")]
    (if (= actual expected)
      (print-successful-test fun-name)
      (print-failed-test fun-name actual expected))))

(defn check-content
  "Sjekk returnet innehold mot forventet innehold"
  [fun-name actual-content expected-content]
  (if (= actual-content expected-content)
    (print-successful-test fun-name)
    (print-failed-test fun-name actual-content expected-content))
  )


;; Tester ---------

(defn test-get-username []
  (print-test-title "get-username")
  ;; test uten args
  (check-content-println "get-username uten args" #(get-username []) "Usage: github-activity <username>")

  ;; test med args
  (check-content "get-username med args" (get-username ["username"]) "username")

  ;; test med flere args kun første input returneres
  (check-content "get-username med flere args" (get-username ["username1" "username2"]) "username1"))


(defn -main [& _]
  (test-get-username)
  )