# -- Builder Image --
# Empty body to trigger ONBUILD steps of base image
FROM rentpath/rp_clojure:onbuild-openjdk8u151-alpine-4 as builder
