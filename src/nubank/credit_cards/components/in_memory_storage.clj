(ns nubank.credit-cards.components.in-memory-storage
  (:require [nubank.credit-cards.domain.ports.storage-client :as storage-client]))

; Define a record that extends StorageClient protocol interface and implement its functions
(defrecord InMemoryStorage [storage]
  storage-client/StorageClient
  (credit-cards [_this] @storage)
  (add-credit-card! [_this credit-card]
    (swap! storage #(assoc % (:credit-card/id credit-card) credit-card))))

(defn new-in-memory []
  "Instantiate the protocol once with an empty object"
  (->InMemoryStorage (atom {})))