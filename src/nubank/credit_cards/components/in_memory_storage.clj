(ns nubank.credit-cards.components.in-memory-storage
  (:require [nubank.credit-cards.domain.ports.storage-client :as storage-client]))

; Define a record that extends StorageClient protocol interface and implement its functions
(defrecord InMemoryStorage [storage]
  storage-client/StorageClient
  (read-all [_this] @storage)
  (put! [_this update-fn] (swap! storage update-fn)))

(defn new-in-memory []
  "Instantiate the protocol once with an empty object"
  (->InMemoryStorage (atom {})))