package com.cengenes.challange.conf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.cassandra.config.DataCenterReplication;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 *
 * @author Enes
 */
@Configuration
@EnableCassandraRepositories("com.cimri.challange.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Autowired
	ApplicationProperties properties;

	@Bean
	@Override
	public CassandraCqlClusterFactoryBean cluster() {
		CassandraCqlClusterFactoryBean bean = new CassandraCqlClusterFactoryBean();
		bean.setKeyspaceCreations(getKeyspaceCreations());
		bean.setContactPoints(properties.getContactPoints());
		bean.setPort(properties.getPort());
		return bean;
	}

	@Override
	protected List<String> getStartupScripts() {

		String script = "CREATE KEYSPACE IF NOT EXISTS cimriKeyspace " + "WITH durable_writes = true "
				+ "AND replication = { 'replication_factor' : 1, 'class' : 'SimpleStrategy' };";

		String tableProduct = "CREATE TABLE IF NOT EXISTS Product (id text PRIMARY KEY,url text,priceOnDateMap map<text,text>);";

		String tableIndex = "CREATE INDEX IF NOT EXISTS URLindex ON Product (url);";

		return Arrays.asList(script, tableProduct, tableIndex);
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Override
	protected String getKeyspaceName() {
		return properties.getKeyspaceName();
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "com.cimri.challange.entity" };
	}

	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		List<CreateKeyspaceSpecification> createKeyspaceSpecifications = new ArrayList<>();
		createKeyspaceSpecifications.add(getKeySpaceSpecification());
		return createKeyspaceSpecifications;
	}

	// Below method creates "keyspace" if it doesnt exist.
	private CreateKeyspaceSpecification getKeySpaceSpecification() {
		CreateKeyspaceSpecification cimriKeySpace = new CreateKeyspaceSpecification();
		DataCenterReplication dcr = new DataCenterReplication("dc1", 3L);
		cimriKeySpace.name(properties.getKeyspaceName());
		cimriKeySpace.ifNotExists(true);
		CreateKeyspaceSpecification.createKeyspace().withNetworkReplication(dcr);
		return cimriKeySpace;
	}

}