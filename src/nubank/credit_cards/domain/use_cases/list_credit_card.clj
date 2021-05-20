(ns nubank.credit-cards.domain.use-cases.list-credit-card
  (:require [nubank.credit-cards.domain.ports.storage-client :as storage-client]))

(defn cards [storage]
  (storage-client/credit-cards storage))
