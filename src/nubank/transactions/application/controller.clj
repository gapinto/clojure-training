(ns nubank.transactions.application.controller
  (:require [nubank.transactions.domain.logic :as logic])
  (:require [nubank.transactions.domain.transaction-service :as service]))

(defn create-transaction! [account-id value company category date storage]
  (let [transaction (logic/new-transaction account-id value company category date)]
    (service/add-transaction! transaction storage)
    transaction))

(defn total-value-by-account-and-category [storage]
  (let [ transactions (service/transactions storage)]
    (logic/total-value-by-account-and-category transactions)))