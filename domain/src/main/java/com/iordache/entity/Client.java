package com.iordache.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String clientId;
    private String grantType;
    private String scope;
    private String secret;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) && Objects.equals(grantType, client.grantType) && Objects.equals(scope, client.scope) && Objects.equals(secret, client.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, grantType, scope, secret);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", grantType='" + grantType + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
