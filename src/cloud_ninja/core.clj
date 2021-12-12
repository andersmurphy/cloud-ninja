(ns cloud-ninja.core
  (:gen-class)
  (:import [cloudNinja Game]))

(defn -main []
  (.init (Game.)))
