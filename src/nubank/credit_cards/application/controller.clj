(ns nubank.credit-cards.application.controller
  (:require [nubank.credit-cards.domain.logic :as logic])
  (:require [nubank.credit-cards.domain.credit-card-service :as service]))

(defn create-credit-card! [account-id number ccv expiration storage]
  (let [credit-card (logic/new-card account-id number ccv expiration)]
    (service/add-credit-card! credit-card storage)
    credit-card))

(defn credit-cards [storage]
  (service/credit-cards storage))