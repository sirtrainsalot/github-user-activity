(ns github-user-activity.impshell
  (:gen-class))

(defn -main [& args]
  (let [username (first args)]
    (if (empty? username)
      (println "Usage: github-activity <username>")
      (println "Lets find some data for" username)))
  )



