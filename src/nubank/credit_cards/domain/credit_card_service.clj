(ns nubank.credit-cards.domain.credit-card-service
  (:require [nubank.credit-cards.domain.ports.storage-client :as storage-client]))

(defn add-credit-card! [credit-card storage]
  ; anonymous function is getting account id and associating with the account object
  ; something like this { 12898989 : { :id 12898989 :account-id 1289545 }}
  (storage-client/put! storage
                       #(assoc % (:id credit-card) credit-card)))

(defn credit-cards [storage]
  (storage-client/read-all storage))