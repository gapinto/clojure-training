(ns nubank.accounts.application.controller
  (:require [nubank.accounts.domain.use-cases.create-account :as create-account])
  (:require [nubank.accounts.domain.use-cases.list-accounts :as list-accounts]))

(defn create-account! [customer-id customer-name credit-limit storage create-credit-cards-fn]
  (create-account/create! customer-id customer-name credit-limit storage create-credit-cards-fn))

(defn accounts [storage]
  (list-accounts/accounts storage))