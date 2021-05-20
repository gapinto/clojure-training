(ns nubank.credit-cards.domain.use-cases.create-credit-card
  (:require [nubank.credit-cards.domain.logic :as logic])
  (:require [nubank.credit-cards.domain.ports.storage-client :as storage-client]))

(defn create! [account-id number ccv expiration storage]
  (let [card (logic/new-card account-id number ccv expiration)]
    (storage-client/add-credit-card! storage card)
    card))
