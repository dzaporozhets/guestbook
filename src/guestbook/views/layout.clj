(ns guestbook.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to guestbook"]
     (include-css "/css/bootstrap.min.css")
     (include-css "/css/screen.css")]
    [:body
     [:div.container body]]))
