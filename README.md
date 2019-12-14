[![Clojars Project](https://img.shields.io/clojars/v/spec-signature.svg)](https://clojars.org/spec-signature)

# spec-signature

This library implements the `sdef` macro, which aims to be a simpler form for
`clojure.spec.alpha/fdef`. Sometimes you simply want to spec a functions
arguments, but don't need the advanced features of `clojure.spec`.

Comparison:


```clojure
;; clojure.spec's version

(fdef create-person
  :args (cat (keyword "0") :person/name
             (keyword "1") :person/age)
  :ret :app/person)
(defn create-person [name age]
  {:person/name name
   :person/age age})


;; Is equal to spec-signature's version:

(sdef create-person [:person/name :person/age] :app/person)
(defn create-person [name age]
  {:person/name name
   :person/age age})
```

## Usage

Creates a spec signature for your function.
This is a shorthand for fdef.

| args    | description                                            |
| ------- | ------------------------------------------------------ |
| `fname` | The function you're writing a signature for            |
| `fargs` | A vector of specs, represents your functions arguments |
| `fret`  | The return spec of your function                       |

Example:

``` Clojure
  (require '[clojure.spec.alpha :as s])
  (require '[spec-signature.core :refer [sdef]])

  (s/def :person/name string?)
  (s/def :person/age int?)

  (s/def :app/person
    (s/keys :req [:person/name
                  :person/age]))

  (sdef create-person [:person/name :person/age] :app/person)
  (defn create-person [name age]
    {:person/name name
     :person/age age})

;; Spec documentation:

;; my-ns/create-person
;;  [name age]
;;   Not documented.

;; Spec:

;; arguments  : (s/cat
;;               :0 :person/name
;;               :1 :person/age)
;; returns    : :app/person
```

## Caveat

Since we don't add keys for our arguments, in the documentation / error messages
they are automatically marked as a range from 0 to the amount of args (see the
spec documentation in the example).

## License

Copyright © 2019 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
