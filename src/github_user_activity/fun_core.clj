(ns github-user-activity.fun_core)

(defn get-username [args]
  (if (empty? args)
    (do
      (println "Usage: github-activity <username>"))
    (first args)))

(defn format-activity [activity]
  (let [{:keys [type repo payload]} activity
        repo-name (:name repo)]
    (case type
      "PushEvent" (str "Pushed" (count (:commits payload)) " commits to " repo-name)
      "IssuesEvent" (str "Opened a new issue in " repo-name)
      "WatchEvent" (str "Starred " repo-name)
      "ForkEvent" (str "Forked " repo-name)
      "PullRequestEvent" (str "Opened a pull request in " repo-name)
      ;; default
      (str type " on " repo-name))))


(defn format-activities [activites]
  (map format-activity activites))



