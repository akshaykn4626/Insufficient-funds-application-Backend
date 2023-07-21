package com.example.InSufficientFunds.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.InSufficientFunds.util.Auditable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSourceStatistics  extends Auditable<String> {

	private long requestsNotYetHandled;

	private long requestApproved;

	private long requestRejected;

}
