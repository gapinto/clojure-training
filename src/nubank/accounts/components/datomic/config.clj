(ns nubank.accounts.components.datomic.config
  (:require [datomic.api :as api]))

(def db-uri "datomic:dev://localhost:4334/account")

(def schema [{:db/ident       :account/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/doc         "Account uuid"},
             {:db/ident       :account/credit-limit
              :db/valueType   :db.type/bigint
              :db/cardinality :db.cardinality/one
              :db/doc         "Credit Limit"},
             {:db/ident       :account/customer-id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/doc         "Customer Id"},
             {:db/ident       :account/customer-name
              :db/valueType   :db.type/string
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
