(ns nubank.transactions.application.controller
  (:require [nubank.transactions.domain.logic :as logic])
  (:require [nubank.transactions.domain.ports.storage-client :as storage-client])
  (:require [nubank.transactions.domain.use-cases.create-transaction :as create-transaction])
  (:require [nubank.transactions.domain.use-cases.list-total-value-by-account-and-category :as total-value-by-account-and-category]))

(defn create-transaction! [account-id value company category date storage]
  (create-transaction/create! account-id value company category date storage))

(defn transactions [storage]
  (storage-client/transactions storage))

(defn total-value-by-account-and-category [storage]
  (total-value-by-account-and-category/list-transactions storage))