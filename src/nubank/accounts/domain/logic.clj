(ns nubank.accounts.domain.logic
  (:import [java.util UUID]))

(defn new-account [customer-id customer-name credit-limit]
  {:id           (UUID/randomUUID)
   :name         customer-name
   :customer-id  customer-id
   :credit-limit credit-limit})
