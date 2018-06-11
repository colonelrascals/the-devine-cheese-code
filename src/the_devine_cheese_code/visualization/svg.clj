(ns the-devine-cheese-code.visualization.svg
  (:require [clojure.string :as s])
  (:refer-clojure :exclude [min max]))


(defn latlng->point 
  [latlng]
  (str (:lng latlng) "," (:lat latlng)))

(defn points
  [locations]
  (clojure.string/join " " (map latlng->point locations)))
   
(defn comparator-over-maps
  [comparison-fn ks]
  (fn [maps]
    (zipmap ks
      (map (fn [k] (apply comparison-fn (map k maps)))
       ks))))  
  
(def min (comparator-over-maps clojure.core/min [:lat :lng]))
(def max (comparator-over-maps clojure.core/max [:lat :lng]))

(defn translate-to-origin
  [locations]
  (let [mincooords (min locations)]
    (map #(merge-with - % mincooords) locations)))

(defn scale
  [width height locations]
  (let [maxcoords (max locations)
        ratio {:lat (/ height (:lat maxcoords))
               :lng (/ width (:lng maxcoords))}]
      (map #(merge-with * % ratio) locations)))

(defn latlng->point
"Convert lat/lng map to comma-separated string."
[latlng]
(str (:lat latlng) "," (:lng latlng)))

(defn points
[locations]
(s/join " " (map latlng->point locations)))

(defn line
[points]
(str "<polyline points=\"" points "\" />"))

(defn transform
"Chain other functions."
[width height locations]
(->> locations
    translate-to-origin
    (scale width height)))

(defn xml
"svg template which also flips the coordinate system."
[width height locations]
(str "<svg height=\"" height "\" width=\"" width "\">"
    "<g transform=\"translate(0," height ")\">"
    "<g transform=\"rotate(-90)\">"
    (-> (transform width height locations)
        points
        line)
    "</g></g>"
        "</svg>"))
