(ns nubank.transactions.components.in-memory-storage
  (:require [nubank.transactions.domain.ports.storage-client :as storage-client]))

; Define a record that extends StorageClient protocol interface and implement its functions
(defrecord InMemoryStorage [storage]
  storage-client/StorageClient
  (transactions [_this] @storage)
  (add-transaction! [_this transaction]
    (swap! storage transaction)))

(defn new-in-memory []
  "Instantiate the protocol once with an empty object"
  (->InMemoryStorage (atom {})))