(ns nubank.accounts.components.datomic.datomic-storage
  (:require [datomic.api :as api])
  (:require [nubank.accounts.domain.ports.storage-client :as storage-client])
  (:require [nubank.accounts.components.datomic.config :as config]))

(defn list-account[conn]
  (api/q '[:find (pull ?entity [*])
           :where [?entity :account/id]] (api/db conn)))

(defn datomic->account-entity [accounts]
  (first accounts))

(defrecord DatomicStorage [conn]
  storage-client/StorageClient
  (add-account! [_this account]
    (api/transact conn [account]))
  (accounts [_this]
    (let [accounts (list-account conn)]
      (map datomic->account-entity accounts))))

(defn connect []
  "Connect to datomic"
  (let [conn (config/prep-datomic)]
    (->DatomicStorage conn)))

(defn drop-database []
  (config/delete-database))
