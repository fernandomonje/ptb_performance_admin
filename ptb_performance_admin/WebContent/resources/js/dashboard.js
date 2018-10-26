	var sessionActive = true;
	$('#carrierDetailModal').on('show.bs.modal',function(e) {
						$("#EditCarrier").off("click");
						var carrierJsonUrl = "CarrierData";
						$("#staticSPID").prop("readonly", true);
						$("#staticName").prop("readonly", true);
						var $radios = $("input:radio[name=carrierStatus]");
						$radios.filter("[value=true]").prop("disabled", true);
						$radios.filter("[value=false]").prop("disabled", true);
						$("#EditCarrier").removeClass("btn-danger").addClass("btn-primary");
						$("#EditCarrier").text("Editar");
						$("#EditCarrier").on("click",function(e) {
											$("#staticName").removeAttr("readonly");
											var $radios = $("input:radio[name=carrierStatus]");
											$radios.filter("[value=true]").prop("disabled", false);
											$radios.filter("[value=false]").prop("disabled", false);
											$("#EditCarrier").removeClass("btn-primary").addClass("btn-danger");
											$("#EditCarrier").text("Salvar");
											$(this).on("click",	function() {
																var CarrierEditUrl = "EditCarrier";
																$.post(CarrierEditUrl,{
																					spid : $("#staticSPID").val(),
																					name : $("#staticName").val(),
																					status : $("input[type=radio][name=carrierStatus]:checked").val()
																		})
																		.done(function(data) {
																					$("#carrierDetailModal").modal("toggle");
																					$("#mainTable").trigger("refresh");
																					$("carrierEditResultModal").modal("toggle");
																		})
																		.fail(function(data) {
																					$("#carrierDetailModal").modal("toggle");
																					$("#mainTable").trigger("refresh");
																					$("carrierEditResultModal").modal("toggle");
																		});
											});
						});
						$.getJSON(carrierJsonUrl, {
									spid : $(e.relatedTarget).data("spid")
								})
								.fail(function(jqXHR, textStatus, errorThrown) {
									if (sessionActive) {
										alert("Falha na requisicao de Json de Operadora. \n Erro: " + textStatus);
									} else {
										alert("Sessao Expirada, voce sera redirecionado para a tela de Login.");
										document.location.href = "Login";
									}
								})
								.done(function(data) {
											var spid = null;
											var name = null;
											var status = null;
											if (data != null) {
												spid = data.Carrier.spid;
												name = data.Carrier.name;
												status = data.Carrier.status;
												$("#HeaderLable").html("Detalhes de Prestadora - [" + spid + "]");
												$("#staticSPID").val(spid);
												$("#staticName").val(name);
												var $radios = $('input:radio[name=carrierStatus]');
												if ($radios.is(':checked') === false) {
													if (status != null) {
														if (status == "1") {
															$radios.filter('[value=true]').prop('checked',true);
														} else {
															$radios.filter('[value=false]').prop('checked',true);
														}
													}
												}
											}
								});
						$("#MeasurementPrimaryTable > tbody").empty();
						$("#MeasurementPrimaryTable > tbody").append("<tr><td colspan=\"5\" align=\"center\">No Data</td></tr>");
						$("#MeasurementSecondaryTable > tbody").empty();
						$("#MeasurementSecondaryTable > tbody").append("<tr><td colspan=\"5\" align=\"center\">No Data</td></tr>");
						var MeasurementDataUrl = "CarrierMeasurementList"
						$.getJSON(MeasurementDataUrl, {
							spid : $(e.relatedTarget).data("spid"),
							environment : "primary"
						})
						.fail(function(jqXHR, textStatus, errorThrown) {
							if (sessionActive) {
								alert("Falha na requisicao de Json de Measurements. \n Erro: " + textStatus);
							} else {
								alert("Sessao Expirada, voce sera redirecionado para a tela de Login.");
								document.location.href = "Login";
							}
						})
						.done(function(data) {
							var spid = $(e.relatedTarget).data("spid");
							if (data != null) {
								if (!data[spid]){
									$("#MeasurementPrimaryTable > tbody").empty();
									$.each(data.Measurement[spid], function() {
										$("#MeasurementPrimaryTable > tbody").append("<tr><td>" + this.datetime +
												"</td><td>" + this.download_bandwidth + 
												"</td><td>" + this.upload_bandwidth +
												"</td><td>" + this.ping_response_time +
												"</td><td>" + Math.floor(this.ping_packet_loss * 100) + "%</td></tr>")
		         					});
								}
							}
						});
						$.getJSON(MeasurementDataUrl, {
							spid : $(e.relatedTarget).data("spid"),
							environment : "secondary"
						})
						.fail(function(jqXHR, textStatus, errorThrown) {
							if (sessionActive) {
								alert("Falha na requisicao de Json de Measurements. \n Erro: " + textStatus);	
							} else {
								alert("Sessao Expirada, voce sera redirecionado para a tela de Login.");
								document.location.href = "Login";
							}
							
						})
						.done(function(data) {
							var spid = $(e.relatedTarget).data("spid");
							if (data != null) {
								if (!data[spid]){
									$("#MeasurementSecondaryTable > tbody").empty();
									$.each(data.Measurement[spid], function() {
										$("#MeasurementSecondaryTable > tbody").append("<tr><td>" + this.datetime +
												"</td><td>" + this.download_bandwidth + 
												"</td><td>" + this.upload_bandwidth +
												"</td><td>" + this.ping_response_time +
												"</td><td>" + Math.floor(this.ping_packet_loss * 100) + "%</td></tr>")
		         					});
								}
							}
						});
						
					});

	$('#ModalDeleteConfirm').on(
			'show.bs.modal',
			function(e) {
				$("#DeleteConfirm").prop("onclick", null).off("click");
				var spid = $(e.relatedTarget).data("spid");
				$(this).find('.modal-title ').text(
						"Confirmar exclusao do SPID " + spid + "?");
				$("#DeleteConfirm").on("click", function() {
					var CarrierDeleteUrl = "DeleteCarrier";
					$.post(CarrierDeleteUrl, {
						spid : spid
					}).done(function(data) {
						$("#ModalDeleteConfirm").modal("toggle");
						$("#mainTable").trigger("refresh");
						$("carrierEditResultModal").modal("toggle");
					}).fail(function(data) {
						$("#ModalDeleteConfirm").modal("toggle");
						$("#mainTable").trigger("refresh");
						$("carrierEditResultModal").modal("toggle");
					});
				});
			});

	$(document).ready(function() {
		var carrierListJsonUrl = "ListCarrier";
		$('#mainTable').DataTable({
						ajax : {
							url : carrierListJsonUrl,
							dataSrc : "Carrier"
						},
						columns : [
							{data : "spid"},
							{data : "name"},
							{data : "status",
								"render" : function(data, type, row, meta) {
												return type === 'display' && data == "1" ? 
													"<span class=\"badge badge-success\">ATIVA</span>"
													: "<span class=\"badge badge-danger\">INATIVA</span>";
												}
							},
							{data : null,
								"render" : function(data, type, row, meta) {
												return "<a href=\"#\" data-toggle=\"modal\" data-target=\"#carrierDetailModal\" data-spid=\"" + row.spid + "\"><span data-feather=\"edit\"></span></a><a href=\"#\" data-toggle=\"modal\" data-target=\"#ModalDeleteConfirm\" data-spid=\"" + row.spid + "\"><span data-feather=\"trash-2\"></span></a>";
											}
							}],
						columnDefs : [ {
								width : "20%",
								targets : 0 },
								{ 
								width : "30%",
								targets : 1
								}, 
								{
								width : "30%",
								targets : 2
								}, 
								{
								width : "20%",
								targets : 3
								} ],
						"autoWidth" : false,
						fixedColumns : true,
						retrieve : true,
						"language" : {
							"search" : "Procurar:",
							"paginate" : {
								"first" : "Primeira Pagina",
								"last" : "Ultima Pagina",
								"next" : "Proxima",
								"previous" : "Anterior"
							},
							"info" : "Exibindo pagina _PAGE_ de _PAGES_",
							"infoFiltered" : " - filtrados de _MAX_ registros",
							"sLengthMenu" : "Mostrar _MENU_ registros",
						},
						"drawCallback" : function() {
							feather.replace();
						}
		});
		$('.dataTables_length').addClass('bs-select');
		calcOffset();
		checkSession();
	});

	$("#InsertCarrier").on("click", function() {
		var CarrierInsertUrl = "InsertCarrier";
		$.post(CarrierInsertUrl, {
			spid : $("#insertSPID").val(),
			name : $("#insertName").val(),
			status : $("input[type=radio][name=insertStatus]:checked").val()
		}).done(function(data) {
			$("#insertCarrierModal").modal("toggle");
			$("#mainTable").trigger("refresh");
			$("carrierEditResultModal").modal("toggle");
		}).fail(function(data) {
			$("#insertCarrierModal").modal("toggle");
			$("#mainTable").trigger("refresh");
			$("carrierEditResultModal").modal("toggle");
		});
	});

	$("#mainTable").on("refresh", function(e) {
		$('#mainTable').DataTable().ajax.reload();
	});

	$('#insertCarrierModal').on('show.bs.modal', function(e) {
		$("#insertSPID").val("");
		$("#insertName").val("");
		$("input[type=radio][name=insertStatus]").prop("checked", false);
	});
	
	function calcOffset() {
	    var serverTime = getCookie('serverTime');
	    serverTime = serverTime==null ? null : Math.abs(serverTime);
	    var clientTimeOffset = (new Date()).getTime() - serverTime;
	    setCookie('clientTimeOffset', clientTimeOffset);
	}
	
	function checkSession() {
	    var sessionExpiry = Math.abs(getCookie('sessionExpiry'));
	    var expDate = new Date(sessionExpiry);
	    var timeOffset = Math.abs(getCookie('clientTimeOffset'));
	    var localTime = (new Date()).getTime();
	    if (localTime - timeOffset > (sessionExpiry+15000)) { // 15 extra seconds to make sure
	    	sessionActive = false;
	    	$("#UserHeader").append(" [Sessao Expirada]");
	        console.log("Session Expired.");
	        document.location.href = "Login";
	    } else {
	        setTimeout('checkSession()', 10000);
	    }
	};
	
	function getCookie(cname) {
	     var name = cname + "=";
	     var ca = document.cookie.split(';');
	     for(var i=0; i<ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1);
	        if(c.indexOf(name) == 0)
	           return c.substring(name.length,c.length);
	     }
	     return "";
	}
	
	function setCookie(cname, cvalue) {
        document.cookie = cname + "=" + cvalue + "; ";
    }

	