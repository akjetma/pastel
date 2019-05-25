(ns pastel.ansi)

(def PREFIX \u001b)

(def CODES
  #::{:reset             [0 0]
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

(defn cmd
  [code]
  (str PREFIX "[" code "m"))

(defn open
  [style]
  (cmd (first (get CODES style))))

(defn close
  [style]
  (cmd (second (get CODES style))))

(defn wrap-style
  [style body]
  (str (open style)
       body
       (close style)))