```clojure
(p/println "normal"
           (p/red "red"
                  (p/bg-blue "red+bluebg"
                             (p/underline "red+bluebg+ul" "ditto")
                             "red+bluebg again"
                             (p/green "green+bluebg"
                                      {:maps "are fine"}
                                      [:actually 'anything-is-okay]
                                      (let [invert-override (comp p/black p/bg-white)]
                                        (invert-override "do whatever"
                                                         "seems fun")
                                        [[[(p/str (p/underline (invert-override "exotic nestings..") (p/green "kinda >_>")))]]])))
                  "red again"
                  (p/underline "red+ul"))
            "back home")
```

![example](demo.png)
