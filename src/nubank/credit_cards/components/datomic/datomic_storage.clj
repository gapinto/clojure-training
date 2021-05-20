(ns nubank.credit-cards.components.datomic.datomic-storage
  (:require [datomic.api :as api])
  (:require [nubank.credit-cards.domain.ports.storage-client :as storage-client])
  (:require [nubank.credit-cards.components.datomic.config :as config]))

(defn list-credit-card[conn]
  (api/q '[:find (pull ?entity [*])
           :where [?entity :credit-card/id]] (api/db conn)))

(defn datomic->credit-card-entity [credit-cards]
  (first credit-cards))

(defrecord DatomicStorage [conn]
  storage-client/StorageClient
  (add-credit-card! [_this credit-card]
     (println (api/transact conn [credit-card])))
  (credit-cards [_this]
    (let [credit-cards (list-credit-card conn)]
      (map datomic->credit-card-entity credit-cards))))

(defn connect []
  "Connect to datomic"
  (let [conn (config/prep-datomic)]
    (->DatomicStorage conn)))

(defn drop-database []
  (config/delete-database))
