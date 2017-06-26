(ns htt-play-game.core
  (:require [play-cljs.core :as p]))

(def invaders-state
  [{:name "invader-one.svg" :width 20 :height 20 :x 0 :y 0}
   {:name "invader-two.png" :width 20 :height 20 :x 0 :y 0}])

(defonce game (p/create-game 500 500))
(defonce state (atom [{:name "invader-one.svg" :width 20 :height 20 :x 100 :y 100}
                      {:name "invader-two.png" :width 20 :height 20 :x 200 :y 200}]))

#_{:invaders invaders-state}


#_(defn invader-gen []
  (let [rnd (rand 350)]
    [[:image {:name "invader-one.svg" :width 50 :height 50 :x 30 :y 80}]
     [:image {:name "invader-two.png" :width 50 :height 50 :x 50 :y 120}]]))

(def main-screen
  (reify
    p/Screen
    (on-show [this]
      (reset! state {:text-x 50 :text-y 30}))
    (on-hide [this])
    (on-render [this]
      (p/render game
        [[:fill {:color "lightblue"}
          [:rect {:x 0 :y 0 :width 500 :height 500}]]
         [:fill {:color "black"}
          [:text {:value "Lets play global thermo-nuclear war"
                  :x (:text-x @state)
                  :y (:text-y @state)
                  :size 16
                  :font "Ubuntu"
                  :style :italic}]
          (for [invader (:invaders state)]
            [:image {:name (invader :name)
                     :width (invader :width)
                     :height (invader :height)
                     :x (invader :x)
                     :y (invader :y)}])]])

      (swap! state update :text-x inc))))

(doto game
  (p/load-image "invader-one.svg")
  (p/load-image "invader-two.png")
  (p/start)
  (p/set-screen main-screen))

