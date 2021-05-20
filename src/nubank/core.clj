(ns nubank.core
  (:use clojure.pprint)
  (:require [nubank.accounts.components.datomic.datomic-storage :as account-datomic-storage])
  (:require [nubank.credit-cards.components.datomic.datomic-storage :as credit-card-datomic-storage])
  (:require [nubank.transactions.components.datomic.datomic-storage :as transaction-datomic-storage])
  (:require [nubank.accounts.components.in-memory-storage :as account-in-memory-storage])
  (:require [nubank.credit-cards.components.in-memory-storage :as credit-cards-in-memory-storage])
  (:require [nubank.transactions.components.in-memory-storage :as transaction-storage])
  (:require [nubank.accounts.application.controller :as account-controller])
  (:require [nubank.credit-cards.application.controller :as credit-card-controller])
  (:require [nubank.transactions.application.controller :as transaction-controller])
  (:require [clojure.data.generators :as generators])
  (:import [java.util UUID]))

;(def account-storage (account-in-memory-storage/new-in-memory))
;(def credit-card-storage (credit-cards-in-memory-storage/new-in-memory))
;(def transaction-storage (transaction-storage/new-in-memory))
(def account-storage (account-datomic-storage/connect))
(def credit-card-storage (credit-card-datomic-storage/connect))
(def transaction-storage  (transaction-datomic-storage/connect))

(defn create-credit-cards
  "Create a credit card when a account is created"
  [account-id]
  (let [number (generators/uniform 1000000000000000 2000000000000000)
        ccv (generators/uniform 100 999)
        expiration (generators/date)]
    (credit-card-controller/create-credit-card! account-id number ccv expiration credit-card-storage)))

(defn generate-accounts-with-credit-cards
  "Setup accounts"
  []
  (account-controller/create-account! (UUID/randomUUID) "Guilherme Andrade" 8000N account-storage create-credit-cards)
  (account-controller/create-account! (UUID/randomUUID) "Luna Gabriele" 8000N account-storage create-credit-cards)
  (account-controller/create-account! (UUID/randomUUID) "Jéssica Gabriele" 2000N account-storage create-credit-cards)
  (println "\n\n")

  (println "********* Accounts ************")
  (pprint (account-controller/accounts account-storage))

  (println "\n********* Credit Cards ************")
  (pprint (credit-card-controller/credit-cards credit-card-storage)))

(defn create-transactions
  [account-id company category value]
  (let [date (generators/date)]
    (transaction-controller/create-transaction! account-id value company category date transaction-storage)))


(defn generate-transactions []
  (let [account-1 (:account/id (first (account-controller/accounts account-storage)))
        account-2 (:account/id (second (account-controller/accounts account-storage)))]

    (create-transactions account-1 "@Xiquetu" "Roupa" 100.0M)
    (create-transactions account-1 "@Xiquetu" "Roupa" 200.0M)
    (create-transactions account-2 "@Xiquetu" "Roupa" 200.0M)
    (create-transactions account-2 "@Udemy" "Estudos" 100.0M)
    (create-transactions account-2 "@Rei da coxinha" "Comida" 100.0M)
    (println "\n********* Transactions ************")

    (pprint (transaction-controller/transactions transaction-storage))))

(defn start []
  (generate-accounts-with-credit-cards)
  (generate-transactions)

  (println "\n********* Total value by account and category ************")
  (pprint (transaction-controller/total-value-by-account-and-category transaction-storage))
  )

(defn stop []
  (account-datomic-storage/drop-database)
  (credit-card-datomic-storage/drop-database)
  (transaction-datomic-storage/drop-database))

(start)
(stop)