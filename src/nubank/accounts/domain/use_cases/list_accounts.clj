(ns nubank.accounts.domain.use-cases.list-accounts
  (:require [nubank.accounts.domain.ports.storage-client :as storage-client]))

(defn accounts [storage]
  (storage-client/accounts storage))
