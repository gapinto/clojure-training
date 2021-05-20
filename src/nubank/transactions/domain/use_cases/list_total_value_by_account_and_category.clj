(ns nubank.transactions.domain.use-cases.list-total-value-by-account-and-category
  (:require [nubank.transactions.domain.ports.storage-client :as storage-client]
            [nubank.transactions.domain.logic :as logic]))

(defn list-transactions [storage]
  (let [transactions (storage-client/transactions storage)]
    (logic/total-value-by-account-and-category transactions)))