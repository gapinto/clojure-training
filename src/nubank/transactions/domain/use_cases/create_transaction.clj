(ns nubank.transactions.domain.use-cases.create-transaction
  (:require [nubank.transactions.domain.logic :as logic]
            [nubank.transactions.domain.ports.storage-client :as storage-client]))

(defn create! [account-id value company category date storage]
  (let [transaction (logic/new-transaction account-id value company category date)]
    (storage-client/add-transaction! storage transaction)
    transaction))