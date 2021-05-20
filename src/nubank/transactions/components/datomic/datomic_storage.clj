(ns nubank.transactions.components.datomic.datomic-storage
  (:require [datomic.api :as api])
  (:require [nubank.transactions.domain.ports.storage-client :as storage-client])
  (:require [nubank.transactions.components.datomic.config :as config]))

(defn list-transactions[conn]
  (api/q '[:find (pull ?entity [*])
           :where [?entity :transaction/id]] (api/db conn)))

(defn datomic->transaction-entity [transaction]
  (first transaction))

(defrecord DatomicStorage [conn]
  storage-client/StorageClient
  (add-transaction! [_this credit-card]
     (println (api/transact conn [credit-card])))
  (transactions [_this]
    (let [transactions (list-transactions conn)]
      (map datomic->transaction-entity transactions))))

(defn connect []
  "Connect to datomic"
  (let [conn (config/prep-datomic)]
    (->DatomicStorage conn)))

(defn drop-database []
  (config/delete-database))
