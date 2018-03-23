
	<style>
		body {
			padding: 10px;
		}
		.frame_labels {
			/* padding: 10px; */
			background-color: silver;
			/* border: 1px solid black; */
			/* height: 210mm; */
			/* width: 150mm; */
			width: 100%;
		}
		.frame_label {
			/* white-space: nowrap; */
			margin: 1px;
			/* width: 70mm; */
			/* height: 32mm; */
			width: 68mm;
			height: 30mm;
			background-color: white;
		}
		.frame_label_head {
			text-align: center;
			border-bottom: solid 1px;
		}
		.frame_label_row {
			/* padding: 1px; */
		}
	</style>

	<main class="frame_labels" layout="column" ng-repeat="reg in labels">
		<div ng-if="$index % 3 === 0" ng-init="group_labels = labels.slice($index, $index + 3)">
			<div layout="row" layout-align:"space-between end" class="frame_label_row">
				<div ng-repeat="item in group_labels">
					<!--  -->
					<div class="frame_label" layout="column">
						<div class="frame_label_head">
							{{ item.lp_TJ_user_id }}
						</div>
						<div>
							<div layout="row">
								<div style="margin: 10px 10px 10px 10px;">
									<img width="50px" src="{{ item.lp_TJ_qr_image_base64 }}" ></img>
								</div>
								<div layout="column" style="margin: 5px 0px 0px 0px;">
									<div>{{ item.lp_json.fld_2 }}</div>
									<div>{{ item.lp_json.fld_3 }}</div>
									<div>{{ item.lp_json.fld_4 }}</div>
									<div>{{ item.lp_json.fld_5 }}</div>
									<div>{{ item.lp_json.fld_6 }}</div>
								</div>
							</div>
						</div>
					 </div>
					<!--  -->
				</div>
			</div>
		</div>
	</main>
