(ns nubank.accounts.components.in-memory-storage
  (:require [nubank.accounts.domain.ports.storage-client :as storage-client]))

; Define a record that extends StorageClient protocol interface and implement its functions
(defrecord InMemoryStorage [storage]
  storage-client/StorageClient
  (add-account! [_this account] (swap! storage #(assoc % (:account/id account) account)))
  (accounts [_this] @storage))

(defn new-in-memory []
  "Instantiate the protocol once with an empty object"
  (->InMemoryStorage (atom {})))