(ns nubank.credit-cards.domain.logic
  (:import [java.util UUID]))

(defn new-card [account-id number ccv expiration]
  {:id         (UUID/randomUUID)
   :account-id account-id
   :number     number
   :ccv        ccv
   :expiration expiration})
