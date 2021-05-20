(ns nubank.accounts.domain.use-cases.create-account
  (:require [nubank.accounts.domain.logic :as logic])
  (:require [nubank.accounts.domain.ports.storage-client :as storage-client]))

(defn create! [customer-id customer-name credit-limit storage create-credit-cards-fn]
  (let [account (logic/new-account customer-id customer-name credit-limit)]
    (storage-client/add-account! storage account)
    (create-credit-cards-fn (:account/id account))
    account))
