(ns the-devine-cheese-code.core
  (:gen-class))
(require 'the-devine-cheese-code.visualization.svg)

(refer 'the-devine-cheese-code.visualization.svg)

(def heists [{:location "Cologne, Germany"
              :cheese-name "Cheese Pretzel"
              :lat 50.95
              :lng 6.97}
             {:location "Zurich, Switzerland"
              :cheese-name "The Standard Emmental"
              :lat 47.37
              :lng 8.55}
             {:location "Marseille, France"
              :cheese-name "Le Fromage de Cosquer"
              :lat 43.30
              :lng 5.37}
             {:location "Zurich, Switzerland"
              :cheese-name "The Lesser Emmental"
              :lat 47.37
              :lng 8.55}
             {:location "Vatican City (The Holy Sea)"
              :cheese-name "The Cheese of Turin"
              :lat 41.90
              :lng 12.45}])
             

             
  



(defn -main
  [& args]
  (println (points heists)))
