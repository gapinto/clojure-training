(ns nubank.accounts.application.controller
  (:require [nubank.accounts.domain.logic :as logic])
  (:require [nubank.accounts.domain.account-service :as service]))

(defn create-account! [customer-id customer-name credit-limit storage create-credit-cards]
  (let [account (logic/new-account customer-id customer-name credit-limit)]
    (service/add-account! account storage)
    (create-credit-cards (:id account))
    account))

(defn accounts [storage]
  (service/accounts storage))