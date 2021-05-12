(ns nubank.accounts.domain.account-service
  (:require [nubank.accounts.domain.ports.storage-client :as storage-client]))

(defn add-account! [account storage]
  ; anonymous function is getting account id and associating with the account object
  ; something like this { 12898989 : { :id 12898989 :name Guilherme }}
  (storage-client/put! storage
                       #(assoc % (:id account) account)))

(defn accounts [storage]
  (storage-client/read-all storage))