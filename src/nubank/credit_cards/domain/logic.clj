(ns nubank.credit-cards.domain.logic
  (:import [java.util UUID]))

(defn new-card [account-id number ccv expiration]
  {:credit-card/id         (UUID/randomUUID)
   :credit-card/account-id account-id
   :credit-card/number     number
   :credit-card/ccv        ccv
   :credit-card/expiration expiration})
