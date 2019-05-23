(ns pastel.core
  (:refer-clojure :exclude [println str]))

(def ANSI-CODES
  {:reset              "[0m"
   :bright             "[1m"
   :blink-slow         "[5m"
   :underline          "[4m"
   :underline-off      "[24m"
   :inverse            "[7m"
   :inverse-off        "[27m"
   :strikethrough      "[9m"
   :strikethrough-off  "[29m"

   :default "[39m"
   :white   "[37m"
   :black   "[30m"
   :red     "[31m"
   :green   "[32m"
   :blue    "[34m"
   :yellow  "[33m"
   :magenta "[35m"
   :cyan    "[36m"

   :bg-default "[49m"
   :bg-white   "[47m"
   :bg-black   "[40m"
   :bg-red     "[41m"
   :bg-green   "[42m"
   :bg-blue    "[44m"
   :bg-yellow  "[43m"
   :bg-magenta "[45m"
   :bg-cyan    "[46m"})

(def ANSI-PREFIX \u001b)

(defn ansi
  [style]
  (clojure.core/str ANSI-PREFIX (get ANSI-CODES style)))

(defn ansi-combo
  [style-seq]
  (apply clojure.core/str (map ansi style-seq)))

(defn wrap-style
  [style-seq body]
  (clojure.core/str (ansi-combo style-seq) 
                    body 
                    (ansi :reset)))

(defn new-pastel-obj
  [root-style body]
  {:styles (list root-style)
   :body body})

(defn pastel-seq?
  [section]
  (true? (:pastel-seq (meta section))))

(defn add-style
  [style pastel-obj]
  (update pastel-obj :styles #(cons style %)))

(defn make-stylus-fn
  [style]
  (fn stylus
    [& body]
    (with-meta
      (mapcat (fn [section]
                (if (pastel-seq? section)
                  (map (partial add-style style) section)
                  (list (new-pastel-obj style section))))
              (interpose " " body))
      {:pastel-seq true})))

(defn render
  [pastel-seq]
  (->> pastel-seq
       (map (fn [pastel-obj] 
              (wrap-style (:styles pastel-obj)
                          (:body pastel-obj))))
       (apply clojure.core/str)))

(defn str
  [& body]
  (->> body
       (map (fn [section]
              (if (pastel-seq? section)
                (render section)
                section)))
       (apply clojure.core/str)))

(defn println
  [& body]
  (->> body
       (interpose " ")
       (apply str)
       (clojure.core/println)))

(def reset (make-stylus-fn :reset))
(def bright (make-stylus-fn :bright))
(def blink-slow (make-stylus-fn :blink-slow))
(def underline (make-stylus-fn :underline))
(def underline-off (make-stylus-fn :underline-off))
(def inverse (make-stylus-fn :inverse))
(def inverse-off (make-stylus-fn :inverse-off))
(def strikethrough (make-stylus-fn :strikethrough))
(def strikethrough-off (make-stylus-fn :strikethrough-off))
(def default (make-stylus-fn :default))
(def white (make-stylus-fn :white))
(def black (make-stylus-fn :black))
(def red (make-stylus-fn :red))
(def green (make-stylus-fn :green))
(def blue (make-stylus-fn :blue))
(def yellow (make-stylus-fn :yellow))
(def magenta (make-stylus-fn :magenta))
(def cyan (make-stylus-fn :cyan))
(def bg-default (make-stylus-fn :bg-default))
(def bg-white (make-stylus-fn :bg-white))
(def bg-black (make-stylus-fn :bg-black))
(def bg-red (make-stylus-fn :bg-red))
(def bg-green (make-stylus-fn :bg-green))
(def bg-blue (make-stylus-fn :bg-blue))
(def bg-yellow (make-stylus-fn :bg-yellow))
(def bg-magenta (make-stylus-fn :bg-magenta))
(def bg-cyan (make-stylus-fn :bg-cyan))
