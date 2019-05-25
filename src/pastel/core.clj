(ns pastel.core
  (:require [pastel.ansi :as ansi]))

(deftype PastelSeq [children]
  Object
  (toString [this] (apply str children)))

(defmethod print-method PastelSeq
  [this ^java.io.Writer w]
  (print-method (.toString this) w))

(defn pastel-seq?
  [section]
  (instance? PastelSeq section))

(defn make-stylus-fn
  [style]
  (fn stylus
    [& body]
    (PastelSeq.
     (mapcat (fn [section]
               (if (pastel-seq? section)
                 (map (partial ansi/wrap-style style) (.children section))
                 (list (ansi/wrap-style style section))))
             body))))

(def reset (make-stylus-fn :pastel.ansi/reset))
(def bold (make-stylus-fn :pastel.ansi/bold))
(def dim (make-stylus-fn :pastel.ansi/dim))
(def italic (make-stylus-fn :pastel.ansi/italic))
(def underline (make-stylus-fn :pastel.ansi/underline))
(def inverse (make-stylus-fn :pastel.ansi/inverse))
(def hidden (make-stylus-fn :pastel.ansi/hidden))
(def strikethrough (make-stylus-fn :pastel.ansi/strikethrough))

(def black (make-stylus-fn :pastel.ansi/black))
(def red (make-stylus-fn :pastel.ansi/red))
(def green (make-stylus-fn :pastel.ansi/green))
(def yellow (make-stylus-fn :pastel.ansi/yellow))
(def blue (make-stylus-fn :pastel.ansi/blue))
(def magenta (make-stylus-fn :pastel.ansi/magenta))
(def cyan (make-stylus-fn :pastel.ansi/cyan))
(def white (make-stylus-fn :pastel.ansi/white))

(def black-bright (make-stylus-fn :pastel.ansi/black-bright))
(def red-bright (make-stylus-fn :pastel.ansi/red-bright))
(def green-bright (make-stylus-fn :pastel.ansi/green-bright))
(def yellow-bright (make-stylus-fn :pastel.ansi/yellow-bright))
(def blue-bright (make-stylus-fn :pastel.ansi/blue-bright))
(def magenta-bright (make-stylus-fn :pastel.ansi/magenta-bright))
(def cyan-bright (make-stylus-fn :pastel.ansi/cyan-bright))
(def white-bright (make-stylus-fn :pastel.ansi/white-bright))

(def bg-black (make-stylus-fn :pastel.ansi/bg-black))
(def bg-red (make-stylus-fn :pastel.ansi/bg-red))
(def bg-green (make-stylus-fn :pastel.ansi/bg-green))
(def bg-yellow (make-stylus-fn :pastel.ansi/bg-yellow))
(def bg-blue (make-stylus-fn :pastel.ansi/bg-blue))
(def bg-magenta (make-stylus-fn :pastel.ansi/bg-magenta))
(def bg-cyan (make-stylus-fn :pastel.ansi/bg-cyan))
(def bg-white (make-stylus-fn :pastel.ansi/bg-white))

(def bg-black-bright (make-stylus-fn :pastel.ansi/bg-black-bright))
(def bg-red-bright (make-stylus-fn :pastel.ansi/bg-red-bright))
(def bg-green-bright (make-stylus-fn :pastel.ansi/bg-green-bright))
(def bg-yellow-bright (make-stylus-fn :pastel.ansi/bg-yellow-bright))
(def bg-blue-bright (make-stylus-fn :pastel.ansi/bg-blue-bright))
(def bg-magenta-bright (make-stylus-fn :pastel.ansi/bg-magenta-bright))
(def bg-cyan-bright (make-stylus-fn :pastel.ansi/bg-cyan-bright))
(def bg-white-bright (make-stylus-fn :pastel.ansi/bg-white-bright))
