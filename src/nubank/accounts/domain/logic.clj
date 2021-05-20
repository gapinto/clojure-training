(ns nubank.accounts.domain.logic
  (:import [java.util UUID]))

(defn new-account [customer-id customer-name credit-limit]
  {:account/id            (UUID/randomUUID)
   :account/customer-name customer-name
   :account/customer-id   customer-id
   :account/credit-limit  credit-limit})
