(ns github-user-activity.is-core
  (:gen-class)
  )

;; konstanter
(def user-name "sirtrainsalot")
(def github-url "https://api.github.com/users/%s/events")
(format github-url user-name)

;; hent data om bruker fra GitHub API

;; håndtere feil, som invalid username eller API failures
(defn get-user-events [username]
  (->> (format github-url username)
       (slurp)
       ))

(get-user-events "sirtrainsalot")

;; send data om bruker til fk-core.print-user


;; les in username som argument i terminal github-activity <username>
(defn -main [& args]
  (let [[username & rest] args]
    (println "username:" username)

    ))