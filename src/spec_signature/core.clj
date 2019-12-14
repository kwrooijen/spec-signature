(ns spec-signature.core
  (:require
   [clojure.spec.alpha]))

(def ^:private arg-range (map (comp keyword str) (range)))

(defmacro sdef
  "Creates a spec signature for your function.
  This is a shorthand for fdef.

  | args    | description                                            |
  | ------- | ------------------------------------------------------ |
  | `fname` | The function you're writing a signature for            |
  | `fargs` | A vector of specs, represents your functions arguments |
  | `fret`  | The return spec of your function                       |

  Example:

  ```clojure
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
  ```
  "
  [fname fargs fret]
  `(clojure.spec.alpha/fdef ~fname
     :args (clojure.spec.alpha/cat ~@(apply concat (map vector arg-range fargs)))
     :ret ~fret))
