(ns nubank.credit-cards.application.controller
  (:require [nubank.credit-cards.domain.use-cases.create-credit-card :as create-card])
  (:require [nubank.credit-cards.domain.use-cases.list-credit-card :as list-cards]))

(defn create-credit-card! [account-id number ccv expiration storage]
  (create-card/create! account-id number ccv expiration storage))

(defn credit-cards [storage]
  (list-cards/cards storage))