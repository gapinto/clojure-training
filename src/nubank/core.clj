(ns nubank.core
  (:require [nubank.accounts.components.in-memory-storage :as account-storage])
  (:require [nubank.credit-cards.components.in-memory-storage :as credit-card-storage])
  (:require [nubank.transactions.components.in-memory-storage :as transaction-storage])
  (:require [nubank.accounts.application.controller :as account-controller])
  (:require [nubank.credit-cards.application.controller :as credit-card-controller])
  (:require [nubank.transactions.application.controller :as transaction-controller])
  (:import (java.time LocalDateTime)))

(def account-storage (account-storage/new-in-memory))
(def credit-card-storage (credit-card-storage/new-in-memory))
(def transaction-storage (transaction-storage/new-in-memory))

(defn create-credit-cards
  "Create a credit card when a account is created"
  [account-id]
  (let [number (range 16)
        ccv (range 3)
        expiration (LocalDateTime/now)]
    (println (credit-card-controller/create-credit-card! account-id number ccv expiration credit-card-storage))))

(defn generate-accounts-with-credit-cards
  "Setup accounts"
  []
  (println "\n\n\n\n\n")
  (println "********* Creating Accounts ************")

  (println (account-controller/create-account! 1 "Guilherme Andrade" 8000 account-storage create-credit-cards))
  (println (account-controller/create-account! 2 "Luna Gabriele" 8000 account-storage create-credit-cards))
  (println (account-controller/create-account! 3 "Jéssica Gabriele" 8000 account-storage create-credit-cards))
  (println (account-controller/accounts account-storage)))

  (println "\n ********* Finishing Accounts Creation ************")

(defn create-transactions
  [account-id company category]
  (let [value (rand-int 4)
        date (LocalDateTime/now)]
    (println (transaction-controller/create-transaction! account-id value company category date transaction-storage))))


(defn generate-transactions []
  (let [account-1 (key (first (account-controller/accounts account-storage)))
        account-2 (key (second (account-controller/accounts account-storage)))]

    (println "\n\n\n\n\n")
    (println "********* Creating Transactions ************")

    (create-transactions account-1 "@Xiquetu" "Roupa")
    (create-transactions account-1 "@Xiquetu" "Roupa")
    (create-transactions account-2 "@Xiquetu" "Roupa")
    (create-transactions account-2 "@Udemy" "Estudos")
    (create-transactions account-2 "@Rei da coxinha" "Comida")))

    (println "\n ********* Finishing Transactions Creation ************")

(defn start
  []
  (generate-accounts-with-credit-cards)
  (generate-transactions)

  (println "\n\n\n\n\n")
  (println "********* Transaction categories by total value ************")
  (println (transaction-controller/total-value-by-account-and-category transaction-storage))
  )

(start)
