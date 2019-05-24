(ns pastel.core)

(def ANSI-PREFIX \u001b)

(def ANSI-CODES
  {:reset             [0 0]
   :bold              [1 22]
   :dim               [2 22]
   :italic            [3 23]
   :underline         [4 24]
   :inverse           [7 27]
   :hidden            [8 28]
   :strikethrough     [9 29]

   :black             [30 39]
   :red               [31 39]
   :green             [32 39]
   :yellow            [33 39]
   :blue              [34 39]
   :magenta           [35 39]
   :cyan              [36 39]
   :white             [37 39]

   :black-bright      [90 39]
   :red-bright        [91 39]
   :green-bright      [92 39]
   :yellow-bright     [93 39]
   :blue-bright       [94 39]
   :magenta-bright    [95 39]
   :cyan-bright       [96 39]
   :white-bright      [97 39]

   :bg-black          [40 49]
   :bg-red            [41 49]
   :bg-green          [42 49]
   :bg-yellow         [43 49]
   :bg-blue           [44 49]
   :bg-magenta        [45 49]
   :bg-cyan           [46 49]
   :bg-white          [47 49]

   :bg-black-bright   [100 49]
   :bg-red-bright     [101 49]
   :bg-green-bright   [102 49]
   :bg-yellow-bright  [103 49]
   :bg-blue-bright    [104 49]
   :bg-magenta-bright [105 49]
   :bg-cyan-bright    [106 49]
   :bg-white-bright   [107 49]})

(defn ansi-cmd
  [code]
  (str ANSI-PREFIX "[" code "m"))

(defn ansi-open
  [style]
  (ansi-cmd (first (get ANSI-CODES style))))

(defn ansi-close
  [style]
  (ansi-cmd (second (get ANSI-CODES style))))

(defn wrap-style
  [style body]
  (str (ansi-open style)
       body
       (ansi-close style)))

(deftype PastelSeq [children]
  Object
  (toString [this] (apply str children)))

(defmethod print-method PastelSeq
  [ps ^java.io.Writer w]
  (.write w (str ps)))

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
                 (map (partial wrap-style style) (.children section))
                 (list (wrap-style style section))))
             body))))

(def reset (make-stylus-fn :reset))
(def bold (make-stylus-fn :bold))
(def dim (make-stylus-fn :dim))
(def italic (make-stylus-fn :italic))
(def underline (make-stylus-fn :underline))
(def inverse (make-stylus-fn :inverse))
(def hidden (make-stylus-fn :hidden))
(def strikethrough (make-stylus-fn :strikethrough))

(def black (make-stylus-fn :black))
(def red (make-stylus-fn :red))
(def green (make-stylus-fn :green))
(def yellow (make-stylus-fn :yellow))
(def blue (make-stylus-fn :blue))
(def magenta (make-stylus-fn :magenta))
(def cyan (make-stylus-fn :cyan))
(def white (make-stylus-fn :white))

(def black-bright (make-stylus-fn :black-bright))
(def red-bright (make-stylus-fn :red-bright))
(def green-bright (make-stylus-fn :green-bright))
(def yellow-bright (make-stylus-fn :yellow-bright))
(def blue-bright (make-stylus-fn :blue-bright))
(def magenta-bright (make-stylus-fn :magenta-bright))
(def cyan-bright (make-stylus-fn :cyan-bright))
(def white-bright (make-stylus-fn :white-bright))

(def bg-black (make-stylus-fn :bg-black))
(def bg-red (make-stylus-fn :bg-red))
(def bg-green (make-stylus-fn :bg-green))
(def bg-yellow (make-stylus-fn :bg-yellow))
(def bg-blue (make-stylus-fn :bg-blue))
(def bg-magenta (make-stylus-fn :bg-magenta))
(def bg-cyan (make-stylus-fn :bg-cyan))
(def bg-white (make-stylus-fn :bg-white))
(def bg-black-bright (make-stylus-fn :bg-black-bright))
(def bg-red-bright (make-stylus-fn :bg-red-bright))
(def bg-green-bright (make-stylus-fn :bg-green-bright))
(def bg-yellow-bright (make-stylus-fn :bg-yellow-bright))
(def bg-blue-bright (make-stylus-fn :bg-blue-bright))
(def bg-magenta-bright (make-stylus-fn :bg-magenta-bright))
(def bg-cyan-bright (make-stylus-fn :bg-cyan-bright))
(def bg-white-bright (make-stylus-fn :bg-white-bright))