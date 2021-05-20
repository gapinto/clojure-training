(ns nubank.credit-cards.components.datomic.config
  (:require [datomic.api :as api]))

(def db-uri "datomic:dev://localhost:4334/credit-card")

(def schema [{:db/ident       :credit-card/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/doc         "Credit Card uuid"},
             {:db/ident       :credit-card/account-id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/doc         "Account uuid"},
             {:db/ident       :credit-card/number
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one
              :db/doc         "Credit Number"},
             {:db/ident       :credit-card/ccv
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one
              :db/doc         "CCV"},
             {:db/ident       :credit-card/expiration
              :db/valueType   :db.type/instant
              :db/cardinality :db.cardinality/one
              :db/doc         "Customer Name"}])

(defn open-connection []
  (api/create-database db-uri)
  (api/connect db-uri))

(defn delete-database []
  (api/delete-database db-uri))

(defn prep-datomic []
  (let [conn (open-connection)]
    (api/transact conn schema)
    conn))