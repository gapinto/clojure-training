(ns nubank.transactions.domain.transaction-service
  (:require [nubank.transactions.domain.ports.storage-client :as storage-client]))

(defn add-transaction! [transaction storage]
  ; anonymous function is getting account id and associating with the account object
  ; something like this { 12898989 : { :id 12898989 :account-id 1289545 }}
  (storage-client/put! storage
                       #(assoc % (:id transaction) transaction)))

(defn transactions [storage]
  (storage-client/read-all storage))