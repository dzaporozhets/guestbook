(ns guestbook.routes.home
  (:require [compojure.core :refer :all]
            [hiccup.form :refer :all]
            [guestbook.views.layout :as layout]))

(defn home [& [name message error]]
  (layout/common 
    [:h1 "Hello World!"]
    [:p "Welcome to my guestbook"]
    (if error
      [:p.alert.alert-danger error])
    (show-guests)
    [:hr]
    (form-to [:post "/"]
           [:p "Name:"]
           (text-field "name" name)
           [:p "Message:"]
           (text-area {:row 10 :cols 40} "message" message)
           [:br]
           (submit-button "Comment"))))  


(defn save-message [name message]
  (cond
    (empty? name)
    (home name message "Some dummy forgot to leave a name")
    (empty? message)
    (home name message "Don't you have something to say?")
    :else
    (do
      (println name message)
      (home))))

(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]} 
         [{:message "Howdy" :name "Bob" :timestamp nil}
           {:message "Howdy" :name "Bob" :timestamp nil}]]
     [:li 
      [:blockquote message]
      [:p "-" [:cite name]]
      [:time timestamp]])])

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [name message] (save-message name message)))
