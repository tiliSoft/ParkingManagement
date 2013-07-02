<%--
/*******************************************************************************************
 * Copyright (c) 2013 Sotiraki Sima                                                         *
 *                                                                                         *
 * This program is free software; you can redistribute it and/or modify it under the terms *
 * of the GNU General Public License as published by the Free Software Foundation; either  *
 * version 2 of the License, or (at your option) any later version.                        *
 *                                                                                         *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY         *
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 	     *
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.                *
 *                                                                                         *
 * You should have received a copy of the GNU General Public License along with this       *
 * program; if not, write to the:                                                          *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,                    *
 * MA  02111-1307, USA.                                                                    *
 *                                                                                         *
 * --------------------------------------------------------------------------------------- *
 * Project:  Parking Management                                                            *
 * WebSite:  http://ww.simasware.com                                                       *
 * Author :  Sotiraki Sima (Sotiraq.Sima@gmail.com)                                         *  
 *                                                                                         *
 *******************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<!-- Modal -->
<div id="New_Contract_Modal" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-body">
		<div class="accordion" id="accordion2">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle collapsed" data-toggle="collapse"
						data-parent="#accordion2" href="#collapseSeven"> <b><div
								id="iu_head_nome">Client Information</div></b>
					</a>
				</div>
				<div id="collapseSeven" class="accordion-body collapse"
					style="height: 0px;">
					<div id="eponimia_col" class="accordion-inner">
						<div class="row">
							<div class="span1">NAME:</div>
							<div class="span4">
								<input name="iu_nome" id="iu_nome" type="text"
									class="input-medium">
							</div>
						</div>

						<div class="row">
							<div class="span1">PHONE:</div>
							<div class="span4">
								<input name="iu_tele" id="iu_tele" type="text"
									class="input-medium">
							</div>
						</div>


						<div class="row">
							<div class="span1">FAX:</div>
							<div class="span4">
								<input name="iu_cel" id="iu_cel" type="text"
									class="input-medium">
							</div>
						</div>

						<div class="row">
							<div class="span1">C.ID.:</div>
							<div class="span4">
								<input name="iu_afm" id="iu_afm" type="text"
									class="input-medium">
							</div>
						</div>

						<div class="row">
							<div class="span1">INFO:</div>
							<div class="span4">
								<input name="iu_doy" id="iu_doy" type="text"
									class="input-medium">
							</div>
						</div>

						<div class="row">
							<div class="span1">ADDRESS:</div>
							<div class="span4">
								<input name="iu_address" id="iu_address" type="text"
									class="input-medium">
							</div>
						</div>

						<div class="row">
							<div class="span1">TYPE:</div>
							<div class="span4">
								<select name="iu_prof" id="iu_prof" name="iu_prof"
									class="input-medium">
									<option value=0 selected="selected">0</option>
									<option value=1>1</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle collapsed" data-toggle="collapse"
						data-parent="#accordion2" href="#collapseTwo"> <b><div
								id="iu_head_targa">Vehicle Details</div></b>
					</a>
				</div>
				<div id="collapseTwo" class="accordion-body collapse in"
					style="height: 0px;">
					<div class="accordion-inner">
						<div class="row">
							<div class="span1">PLATE:</div>
							<div class="span4">
								<input name="iu_targa" id="iu_targa" type="text"
									class="input-medium">
							</div>
						</div>

						<div class="row">
							<div class="span1">MODEL:</div>
							<div class="span4">
								<input name="iu_modello" id="iu_modello" class="input-medium"
									type="text">
							</div>
						</div>

						<div class="row">
							<div class="span1">TYPE:</div>
							<div class="span4">
								<select name="iu_typo_amak" id="iu_typo_amak"
									class="input-medium" name="typo_amaksi">
									<option value="1" selected="selected">CAR</option>
									<option value="2">MOTOR BIKE</option>
									<option value="3">BIKE</option>
								</select>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle" data-toggle="collapse"
						data-parent="#accordion2" href="#collapseThree"> <b><div
								id="iu_head_posto">Contract</div></b>
					</a>
				</div>
				<div id="collapseThree" class="accordion-body collapse"
					style="height: 0px;">
					<div class="accordion-inner">
						<div class="row">
							<div class="span1">LOCATION:</div>
							<div class="span4">
								<select name='iu_posto' id='iu_posto'><option
										value='ΙΣΟΓΕΙΟ - ΥΠΟΓΕΙΟ' selected='selected'>GROUND
										- UNDERGROUND</option>
									<option value='101'>101</option>
									<option value='102'>102</option>
									<option value='103'>103</option>
									<option value='104'>104</option>
									<option value='105'>105</option>
									<option value='106'>106</option>
									<option value='107A'>107A</option>
									<option value='107B'>107B</option>
									<option value='108'>108</option>
									<option value='109'>109</option>
									<option value='110'>110</option>
									<option value='111'>111</option>
									<option value='112'>112</option>
									<option value='113'>113</option>
									<option value='114'>114</option>
									<option value='115'>115</option>
									<option value='116'>116</option>
									<option value='117'>117</option>
									<option value='118'>118</option>
									<option value='119'>119</option>
									<option value='120'>120</option>
									<option value='121'>121</option>
									<option value='122'>122</option>
									<option value='123'>123</option>
									<option value='124'>124</option>
									<option value='125'>125</option>
									<option value='126'>126</option>
									<option value='127'>127</option>
									<option value='128'>128</option>
									<option value='129'>129</option>
									<option value='130'>130</option>
									<option value='131'>131</option>
									<option value='132'>132</option>
									<option value='133'>133</option>
									<option value='134'>134</option>
									<option value='135'>135</option>
									<option value='136'>136</option>
									<option value='137'>137</option>
									<option value='138'>138</option>
									<option value='139'>139</option>
									<option value='140'>140</option>
									<option value='201'>201</option>
									<option value='202'>202</option>
									<option value='203'>203</option>
									<option value='204'>204</option>
									<option value='205'>205</option>
									<option value='206'>206</option>
									<option value='207A'>207A</option>
									<option value='207B'>207B</option>
									<option value='208'>208</option>
									<option value='209'>209</option>
									<option value='210'>210</option>
									<option value='211'>211</option>
									<option value='212'>212</option>
									<option value='213'>213</option>
									<option value='214'>214</option>
									<option value='215'>215</option>
									<option value='216'>216</option>
									<option value='217'>217</option>
									<option value='218'>218</option>
									<option value='219'>219</option>
									<option value='220'>220</option>
									<option value='221'>221</option>
									<option value='222'>222</option>
									<option value='223'>223</option>
									<option value='224'>224</option>
									<option value='225'>225</option>
									<option value='226'>226</option>
									<option value='227'>227</option>
									<option value='228'>228</option>
									<option value='229'>229</option>
									<option value='230'>230</option>
									<option value='231'>231</option>
									<option value='232'>232</option>
									<option value='233'>233</option>
									<option value='234'>234</option>
									<option value='235'>235</option>
									<option value='236'>236</option>
									<option value='237'>237</option>
									<option value='238'>238</option>
									<option value='239'>239</option>
									<option value='240'>240</option>
									<option value='241'>241</option>
									<option value='242'>242</option>
									<option value='301'>301</option>
									<option value='302'>302</option>
									<option value='303'>303</option>
									<option value='304'>304</option>
									<option value='305'>305</option>
									<option value='306'>306</option>
									<option value='307A'>307A</option>
									<option value='307B'>307B</option>
									<option value='308'>308</option>
									<option value='309'>309</option>
									<option value='310'>310</option>
									<option value='311'>311</option>
									<option value='312'>312</option>
									<option value='313'>313</option>
									<option value='314'>314</option>
									<option value='315'>315</option>
									<option value='316'>316</option>
									<option value='317'>317</option>
									<option value='318'>318</option>
									<option value='319'>319</option>
									<option value='320'>320</option>
									<option value='321'>321</option>
									<option value='322'>322</option>
									<option value='323'>323</option>
									<option value='324'>324</option>
									<option value='325'>325</option>
									<option value='326'>326</option>
									<option value='327'>327</option>
									<option value='328'>328</option>
									<option value='329'>329</option>
									<option value='330'>330</option>
									<option value='331'>331</option>
									<option value='332'>332</option>
									<option value='333'>333</option>
									<option value='334'>334</option>
									<option value='335'>335</option>
									<option value='336'>336</option>
									<option value='337'>337</option>
									<option value='338'>338</option>
									<option value='339'>339</option>
									<option value='340'>340</option>
									<option value='341'>341</option>
									<option value='342'>342</option>
									<option value='401'>401</option>
									<option value='402'>402</option>
									<option value='403'>403</option>
									<option value='404'>404</option>
									<option value='405'>405</option>
									<option value='406'>406</option>
									<option value='407A'>407A</option>
									<option value='407B'>407B</option>
									<option value='408'>408</option>
									<option value='409'>409</option>
									<option value='410'>410</option>
									<option value='411'>411</option>
									<option value='412'>412</option>
									<option value='413'>413</option>
									<option value='414'>414</option>
									<option value='415'>415</option>
									<option value='416'>416</option>
									<option value='417'>417</option>
									<option value='418'>418</option>
									<option value='419'>419</option>
									<option value='420'>420</option>
									<option value='421'>421</option>
									<option value='422'>422</option>
									<option value='423'>423</option>
									<option value='424'>424</option>
									<option value='425'>425</option>
									<option value='426'>426</option>
									<option value='427'>427</option>
									<option value='428'>428</option>
									<option value='429'>429</option>
									<option value='430'>430</option>
									<option value='431'>431</option>
									<option value='432'>432</option>
									<option value='433'>433</option>
									<option value='434'>434</option>
									<option value='435'>435</option>
									<option value='436'>436</option>
									<option value='437'>437</option>
									<option value='438'>438</option>
									<option value='439'>439</option>
									<option value='440'>440</option>
									<option value='441'>441</option>
									<option value='442'>442</option>
									<option value='501'>501</option>
									<option value='502'>502</option>
									<option value='503'>503</option>
									<option value='504'>504</option>
									<option value='505'>505</option>
									<option value='506'>506</option>
									<option value='507A'>507A</option>
									<option value='507B'>507B</option>
									<option value='508'>508</option>
									<option value='509'>509</option>
									<option value='510'>510</option>
									<option value='511'>511</option>
									<option value='512'>512</option>
									<option value='513'>513</option>
									<option value='514'>514</option>
									<option value='515'>515</option>
									<option value='516'>516</option>
									<option value='517'>517</option>
									<option value='518'>518</option>
									<option value='519'>519</option>
									<option value='520'>520</option>
									<option value='521'>521</option>
									<option value='522'>522</option>
									<option value='523'>523</option>
									<option value='524'>524</option>
									<option value='525'>525</option>
									<option value='526'>526</option>
									<option value='527'>527</option>
									<option value='528'>528</option>
									<option value='529'>529</option>
									<option value='530'>530</option>
									<option value='531'>531</option>
									<option value='532'>532</option>
									<option value='533'>533</option>
									<option value='534'>534</option>
									<option value='535'>535</option>
									<option value='536'>536</option>
									<option value='537'>537</option>
									<option value='538'>538</option>
									<option value='539'>539</option>
									<option value='540'>540</option>
									<option value='541'>541</option>
									<option value='542'>542</option>
									<option value='601'>601</option>
									<option value='602'>602</option>
									<option value='603'>603</option>
									<option value='604'>604</option>
									<option value='605'>605</option>
									<option value='606'>606</option>
									<option value='607A'>607A</option>
									<option value='607B'>607B</option>
									<option value='608'>608</option>
									<option value='609'>609</option>
									<option value='610'>610</option>
									<option value='611'>611</option>
									<option value='612'>612</option>
									<option value='613'>613</option>
									<option value='614'>614</option>
									<option value='615'>615</option>
									<option value='616'>616</option>
									<option value='617'>617</option>
									<option value='618'>618</option>
									<option value='619'>619</option>
									<option value='620'>620</option>
									<option value='621'>621</option>
									<option value='622'>622</option>
									<option value='623'>623</option>
									<option value='624'>624</option>
									<option value='625'>625</option>
									<option value='626'>626</option>
									<option value='627'>627</option>
									<option value='628'>628</option>
									<option value='629'>629</option>
									<option value='630'>630</option>
									<option value='631'>631</option>
									<option value='632'>632</option>
									<option value='633'>633</option>
									<option value='634'>634</option>
									<option value='635'>635</option>
									<option value='636'>636</option>
									<option value='637'>637</option>
									<option value='638'>638</option>
									<option value='639'>639</option>
									<option value='640'>640</option>
									<option value='641'>641</option>
									<option value='642'>642</option>
									<option value='701'>701</option>
									<option value='702'>702</option>
									<option value='703'>703</option>
									<option value='704'>704</option>
									<option value='705'>705</option>
									<option value='706'>706</option>
									<option value='707'>707</option>
									<option value='708'>708</option>
									<option value='709'>709</option>
									<option value='710'>710</option>
									<option value='711'>711</option>
									<option value='712'>712</option>
									<option value='713'>713</option>
									<option value='714'>714</option>
									<option value='715'>715</option>
									<option value='716'>716</option>
									<option value='717'>717</option>
									<option value='718'>718</option>
									<option value='719'>719</option>
									<option value='720'>720</option>
									<option value='721A'>721A</option>
									<option value='721B'>721B</option>
									<option value='722'>722</option>
									<option value='723'>723</option>
									<option value='724'>724</option>
									<option value='725'>725</option>
									<option value='726'>726</option>
									<option value='727'>727</option>
									<option value='728'>728</option>
									<option value='729'>729</option>
									<option value='730'>730</option>
									<option value='731'>731</option>
									<option value='732'>732</option>
									<option value='733'>733</option>
									<option value='734'>734</option>
									<option value='735'>735</option>
									<option value='RAMP DN A'>RAMP DN A</option>
									<option value='RAMP UP B'>RAMP UP B</option></select>
							</div>
						</div>
						<div class="row">
							<div class="span1">RENT:</div>
							<div class="span4">
								<input onkeyup="correct_number(event,'iu_prezzo');"
									name="iu_prezzo" id="iu_prezzo" type="text" class="input-small"
									value="0">
							</div>
						</div>
						<div style='display: none;' class="row">
							<div class="span1">Date / Re:</div>
							<div class="span4">
								<input name="iu_data_ricevutta" id="iu_data_ricevutta"
									class="input-small" type="text">
							</div>
						</div>
						<div id="AA_In_Contract" class="row">
							<div class="span1">Α/Α:</div>
							<div class="span4">
								<input onkeyup="correct_number(event,'iu_caa');" name="iu_caa"
									id="iu_caa" type="text" class="input-small" value="0">
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<div id="msgbox_modal"></div>
		<button id="b_cancel" class="btn btn-danger" onclick="Clean_Modal();"
			data-dismiss="modal" aria-hidden="true">Cancel</button>
		<button id="b_save" class="btn btn-success"
			onclick="Save_New_Contract();" aria-hidden="true">Save</button>
		<button id="b_close" style="display: none" class="btn btn-mini"
			onclick="Clean_Modal();" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>

<div id="Contract_Modal">

	<div id="Contract_Modal_body" style='display: none;'>

		<div class="accordion" id="saccordion2">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle collapsed" data-toggle="collapse"
						data-parent="#saccordion2" href="#scollapseSeven"> <b><div
								id="siu_head_nome">- NAME -</div></b>
					</a>
				</div>
				<div id="scollapseSeven" class="accordion-body collapse"
					style="height: 0px;">
					<div class="accordion-inner">



						<div class="row">
							<div class="span1">NAME:</div>
							<div class="span4">
								<b><span id="siu_nome_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_nome');" style='display: none;'
									name="siu_nome" id="siu_nome" type="text" class="input-medium"><a
									id="siu_nome_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_nome');" src="img/pencil.png" /></a>
							</div>
						</div>

						<div class="row">
							<div class="span1">PHONE:</div>
							<div class="span4">
								<b><span id="siu_tele_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_tele');" style='display: none;'
									name="siu_tele" id="siu_tele" type="text" class="input-medium"><a
									id="siu_tele_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_tele');" src="img/pencil.png" /></a>
							</div>
						</div>


						<div class="row">
							<div class="span1">FAX:</div>
							<div class="span4">
								<b><span id="siu_cel_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_cel');" style='display: none;'
									name="siu_cel" id="siu_cel" type="text" class="input-medium"><a
									id="siu_cel_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_cel');" src="img/pencil.png" /></a>
							</div>
						</div>

						<div class="row">
							<div class="span1">C.ID:</div>
							<div class="span4">
								<b><span id="siu_afm_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_afm');" style='display: none;'
									name="siu_afm" id="siu_afm" type="text" class="input-medium"><a
									id="siu_afm_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_afm');" src="img/pencil.png" /></a>
							</div>
						</div>

						<div class="row">
							<div class="span1">INFO:</div>
							<div class="span4">
								<b><span id="siu_doy_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_doy');" style='display: none;'
									name="siu_doy" id="siu_doy" type="text" class="input-medium"><a
									id="siu_doy_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_doy');" src="img/pencil.png" /></a>
							</div>
						</div>

						<div class="row">
							<div class="span1">ADDRESS:</div>
							<div class="span4">
								<b><span id="siu_address_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_address');"
									style='display: none;' name="siu_address" id="siu_address"
									type="text" class="input-medium"><a
									id="siu_address_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_address');" src="img/pencil.png" /></a>
							</div>
						</div>

						<div class="row">
							<div class="span1">TYPE:</div>
							<div class="span4">
								<b><span id="siu_prof_span"></span></b> <select
									onchange="key_press_(event,'siu_prof');" style='display: none;'
									name="siu_prof" id="siu_prof" name="iu_prof"
									class="input-medium">
									<option value=0 selected="selected">0</option>
									<option value=1>1</option>
								</select><a id="siu_prof_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_prof');" src="img/pencil.png" /></a>
							</div>
						</div>


					</div>
				</div>
			</div>
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle collapsed" data-toggle="collapse"
						data-parent="#saccordion2" href="#scollapseTwo"> <b><div
								id="siu_head_targa">- Plate -</div></b>
					</a>
				</div>
				<div id="scollapseTwo" class="accordion-body collapse"
					style="height: 0px;">
					<div class="accordion-inner">

						<div id="gridbox_cars"
							style="width: 60%; height: 180px; background-color: white;"></div>
						<a href="javascript:void(null);" onclick="add_New_Row_cars();"><img
							src="img/add.png" />Add</a> &nbsp; | &nbsp; <a
							href="javascript:void(null);" onclick="removeRow_cars();"><img
							src="img/remove.png" />Removing</a> &nbsp; | &nbsp; <a
							id="save_cars_save" href="javascript:void(null);"
							onclick="saveRows_cars();"><img src="img/save.png" />Αποθήκευση</a>
						&nbsp; <span id="msgbox_cars"></span>
					</div>
				</div>
			</div>
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle" data-toggle="collapse"
						data-parent="#saccordion2" href="#scollapseThree"> <b><div
								id="siu_head_posto">- LOCATION -</div></b>
					</a>
				</div>
				<div id="scollapseThree" class="accordion-body collapse"
					style="height: 0px;">
					<div class="accordion-inner">

						<div class="row">
							<div class="span1">LOCATION:</div>
							<div class="span4">
								<b><span id="siu_posto_span"></span></b> <select
									onchange="key_press_(event,'siu_posto');"
									style='display: none;' name='siu_posto' id='siu_posto'
									class="input-medium"><option value='ΙΣΟΓΕΙΟ - ΥΠΟΓΕΙΟ'
										selected='selected'>GROUND - UNDERGROUND</option>
									<option value='101'>101</option>
									<option value='102'>102</option>
									<option value='103'>103</option>
									<option value='104'>104</option>
									<option value='105'>105</option>
									<option value='106'>106</option>
									<option value='107A'>107A</option>
									<option value='107B'>107B</option>
									<option value='108'>108</option>
									<option value='109'>109</option>
									<option value='110'>110</option>
									<option value='111'>111</option>
									<option value='112'>112</option>
									<option value='113'>113</option>
									<option value='114'>114</option>
									<option value='115'>115</option>
									<option value='116'>116</option>
									<option value='117'>117</option>
									<option value='118'>118</option>
									<option value='119'>119</option>
									<option value='120'>120</option>
									<option value='121'>121</option>
									<option value='122'>122</option>
									<option value='123'>123</option>
									<option value='124'>124</option>
									<option value='125'>125</option>
									<option value='126'>126</option>
									<option value='127'>127</option>
									<option value='128'>128</option>
									<option value='129'>129</option>
									<option value='130'>130</option>
									<option value='131'>131</option>
									<option value='132'>132</option>
									<option value='133'>133</option>
									<option value='134'>134</option>
									<option value='135'>135</option>
									<option value='136'>136</option>
									<option value='137'>137</option>
									<option value='138'>138</option>
									<option value='139'>139</option>
									<option value='140'>140</option>
									<option value='201'>201</option>
									<option value='202'>202</option>
									<option value='203'>203</option>
									<option value='204'>204</option>
									<option value='205'>205</option>
									<option value='206'>206</option>
									<option value='207A'>207A</option>
									<option value='207B'>207B</option>
									<option value='208'>208</option>
									<option value='209'>209</option>
									<option value='210'>210</option>
									<option value='211'>211</option>
									<option value='212'>212</option>
									<option value='213'>213</option>
									<option value='214'>214</option>
									<option value='215'>215</option>
									<option value='216'>216</option>
									<option value='217'>217</option>
									<option value='218'>218</option>
									<option value='219'>219</option>
									<option value='220'>220</option>
									<option value='221'>221</option>
									<option value='222'>222</option>
									<option value='223'>223</option>
									<option value='224'>224</option>
									<option value='225'>225</option>
									<option value='226'>226</option>
									<option value='227'>227</option>
									<option value='228'>228</option>
									<option value='229'>229</option>
									<option value='230'>230</option>
									<option value='231'>231</option>
									<option value='232'>232</option>
									<option value='233'>233</option>
									<option value='234'>234</option>
									<option value='235'>235</option>
									<option value='236'>236</option>
									<option value='237'>237</option>
									<option value='238'>238</option>
									<option value='239'>239</option>
									<option value='240'>240</option>
									<option value='241'>241</option>
									<option value='242'>242</option>
									<option value='301'>301</option>
									<option value='302'>302</option>
									<option value='303'>303</option>
									<option value='304'>304</option>
									<option value='305'>305</option>
									<option value='306'>306</option>
									<option value='307A'>307A</option>
									<option value='307B'>307B</option>
									<option value='308'>308</option>
									<option value='309'>309</option>
									<option value='310'>310</option>
									<option value='311'>311</option>
									<option value='312'>312</option>
									<option value='313'>313</option>
									<option value='314'>314</option>
									<option value='315'>315</option>
									<option value='316'>316</option>
									<option value='317'>317</option>
									<option value='318'>318</option>
									<option value='319'>319</option>
									<option value='320'>320</option>
									<option value='321'>321</option>
									<option value='322'>322</option>
									<option value='323'>323</option>
									<option value='324'>324</option>
									<option value='325'>325</option>
									<option value='326'>326</option>
									<option value='327'>327</option>
									<option value='328'>328</option>
									<option value='329'>329</option>
									<option value='330'>330</option>
									<option value='331'>331</option>
									<option value='332'>332</option>
									<option value='333'>333</option>
									<option value='334'>334</option>
									<option value='335'>335</option>
									<option value='336'>336</option>
									<option value='337'>337</option>
									<option value='338'>338</option>
									<option value='339'>339</option>
									<option value='340'>340</option>
									<option value='341'>341</option>
									<option value='342'>342</option>
									<option value='401'>401</option>
									<option value='402'>402</option>
									<option value='403'>403</option>
									<option value='404'>404</option>
									<option value='405'>405</option>
									<option value='406'>406</option>
									<option value='407A'>407A</option>
									<option value='407B'>407B</option>
									<option value='408'>408</option>
									<option value='409'>409</option>
									<option value='410'>410</option>
									<option value='411'>411</option>
									<option value='412'>412</option>
									<option value='413'>413</option>
									<option value='414'>414</option>
									<option value='415'>415</option>
									<option value='416'>416</option>
									<option value='417'>417</option>
									<option value='418'>418</option>
									<option value='419'>419</option>
									<option value='420'>420</option>
									<option value='421'>421</option>
									<option value='422'>422</option>
									<option value='423'>423</option>
									<option value='424'>424</option>
									<option value='425'>425</option>
									<option value='426'>426</option>
									<option value='427'>427</option>
									<option value='428'>428</option>
									<option value='429'>429</option>
									<option value='430'>430</option>
									<option value='431'>431</option>
									<option value='432'>432</option>
									<option value='433'>433</option>
									<option value='434'>434</option>
									<option value='435'>435</option>
									<option value='436'>436</option>
									<option value='437'>437</option>
									<option value='438'>438</option>
									<option value='439'>439</option>
									<option value='440'>440</option>
									<option value='441'>441</option>
									<option value='442'>442</option>
									<option value='501'>501</option>
									<option value='502'>502</option>
									<option value='503'>503</option>
									<option value='504'>504</option>
									<option value='505'>505</option>
									<option value='506'>506</option>
									<option value='507A'>507A</option>
									<option value='507B'>507B</option>
									<option value='508'>508</option>
									<option value='509'>509</option>
									<option value='510'>510</option>
									<option value='511'>511</option>
									<option value='512'>512</option>
									<option value='513'>513</option>
									<option value='514'>514</option>
									<option value='515'>515</option>
									<option value='516'>516</option>
									<option value='517'>517</option>
									<option value='518'>518</option>
									<option value='519'>519</option>
									<option value='520'>520</option>
									<option value='521'>521</option>
									<option value='522'>522</option>
									<option value='523'>523</option>
									<option value='524'>524</option>
									<option value='525'>525</option>
									<option value='526'>526</option>
									<option value='527'>527</option>
									<option value='528'>528</option>
									<option value='529'>529</option>
									<option value='530'>530</option>
									<option value='531'>531</option>
									<option value='532'>532</option>
									<option value='533'>533</option>
									<option value='534'>534</option>
									<option value='535'>535</option>
									<option value='536'>536</option>
									<option value='537'>537</option>
									<option value='538'>538</option>
									<option value='539'>539</option>
									<option value='540'>540</option>
									<option value='541'>541</option>
									<option value='542'>542</option>
									<option value='601'>601</option>
									<option value='602'>602</option>
									<option value='603'>603</option>
									<option value='604'>604</option>
									<option value='605'>605</option>
									<option value='606'>606</option>
									<option value='607A'>607A</option>
									<option value='607B'>607B</option>
									<option value='608'>608</option>
									<option value='609'>609</option>
									<option value='610'>610</option>
									<option value='611'>611</option>
									<option value='612'>612</option>
									<option value='613'>613</option>
									<option value='614'>614</option>
									<option value='615'>615</option>
									<option value='616'>616</option>
									<option value='617'>617</option>
									<option value='618'>618</option>
									<option value='619'>619</option>
									<option value='620'>620</option>
									<option value='621'>621</option>
									<option value='622'>622</option>
									<option value='623'>623</option>
									<option value='624'>624</option>
									<option value='625'>625</option>
									<option value='626'>626</option>
									<option value='627'>627</option>
									<option value='628'>628</option>
									<option value='629'>629</option>
									<option value='630'>630</option>
									<option value='631'>631</option>
									<option value='632'>632</option>
									<option value='633'>633</option>
									<option value='634'>634</option>
									<option value='635'>635</option>
									<option value='636'>636</option>
									<option value='637'>637</option>
									<option value='638'>638</option>
									<option value='639'>639</option>
									<option value='640'>640</option>
									<option value='641'>641</option>
									<option value='642'>642</option>
									<option value='701'>701</option>
									<option value='702'>702</option>
									<option value='703'>703</option>
									<option value='704'>704</option>
									<option value='705'>705</option>
									<option value='706'>706</option>
									<option value='707'>707</option>
									<option value='708'>708</option>
									<option value='709'>709</option>
									<option value='710'>710</option>
									<option value='711'>711</option>
									<option value='712'>712</option>
									<option value='713'>713</option>
									<option value='714'>714</option>
									<option value='715'>715</option>
									<option value='716'>716</option>
									<option value='717'>717</option>
									<option value='718'>718</option>
									<option value='719'>719</option>
									<option value='720'>720</option>
									<option value='721A'>721A</option>
									<option value='721B'>721B</option>
									<option value='722'>722</option>
									<option value='723'>723</option>
									<option value='724'>724</option>
									<option value='725'>725</option>
									<option value='726'>726</option>
									<option value='727'>727</option>
									<option value='728'>728</option>
									<option value='729'>729</option>
									<option value='730'>730</option>
									<option value='731'>731</option>
									<option value='732'>732</option>
									<option value='733'>733</option>
									<option value='734'>734</option>
									<option value='735'>735</option>
									<option value='RAMP DN A'>RAMP DN A</option>
									<option value='RAMP UP B'>RAMP UP B</option></select><a
									id="siu_posto_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_posto');" src="img/pencil.png" /></a>
							</div>
						</div>

						<div class="row">
							<div class="span1">RENT:</div>
							<div class="span4">
								<b><span id="siu_prezzo_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_prezzo');"
									style='display: none;' name="siu_prezzo" id="siu_prezzo"
									type="text" class="input-medium"><a
									id="siu_prezzo_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_prezzo');" src="img/pencil.png" /></a>
							</div>
						</div>

						<div style='display: none;' class="row">
							<div class="span1">Date / Re:</div>
							<div class="span4">
								<b><span id="siu_data_ricevutta_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_data_ricevutta');"
									style='display: none;' name="siu_data_ricevutta"
									id="siu_data_ricevutta" type="text" class="input-medium"><a
									id="siu_data_ricevutta_pencil" onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_data_ricevutta');"
									src="img/pencil.png" /></a>
							</div>
						</div>
						<div id="id_aa_show" class="row">
							<div class="span1">Α/Α:</div>
							<div class="span4">
								<b><span id="siu_caa_span">sotiris</span></b><input
									onkeyup="key_press_(event,'siu_caa');" style='display: none;'
									name="siu_data_ricevutta" id="siu_caa" type="text"
									class="input-medium"><a id="siu_caa_pencil"
									onclick="return false;" href="#"><img
									onclick="go_to_edit('siu_caa');" src="img/pencil.png" /></a>
							</div>
						</div>
						<div id="siu_extra" style="display: none" val="0"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="con_gridbox" align="center">
			<div id="gridbox"
				style="width: 97%; height: 298px; background-color: white;"></div>

			<div align="left">
				<div align="left"
					style="position: relative; left: 14px; width: 80%;">
					<a href="javascript:void(null);" onclick="addRow_scontrini();"><img
						src="img/add.png" />Add</a> &nbsp; | &nbsp; <a
						href="javascript:void(null);" onclick="removeRow_scontrini();"><img
						src="img/remove.png" />Removing</a> &nbsp; | &nbsp; <a
						id="save_scontrini_save" href="javascript:void(null);"
						onclick="saveRows_scontrini();"><img src="img/save.png" />Save</a>
					&nbsp; <span id="msgbox_scontrini"></span>
				</div>
			</div>

		</div>
		<div id="con_gridbox_extra" align="center">
			<h3>Temporary</h3>
			<div id="msgbox_start_date"></div>
		</div>
	</div>
	<div class="modal-footer">
		<div id="msgbox_modal_save"></div>
		<button id="sb_delete" style="display: none" class="btn btn-danger"
			onclick="Delete_Contract_Question();">
			<i class="icon-trash icon-white"></i>&nbsp;<b>End of contract</b>
		</button>
	</div>
</div>

<div id="Dialog_Modal" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div id="Dialog_Modal_Body" class="modal-body">
		You're sure to erase?
		<button onclick='Delete_Contract();' class='btn btn-small btn-danger'
			type='button'>
			<b>Nai</b>
		</button>
		<button onclick='Non_Delete_Contract();'
			class='btn btn-small btn-success' type='button'>
			<b>NO</b>,Do not delete the CONTRACT
		</button>
		<br>
		<br>Cause deletion: <input id='why_del' type='text'></input>
	</div>
	<div id="Dialog_Modal_Body_ok" class="modal-body"></div>
</div>

<br>
<br>
<div class="footer_div">
	<p style="color: #C3C3C3">Copyright &copy; ---
		2013 - (v1.34 - Powered by <a href="http://ww.simasware.com" target="_blank">SimaS</a>)</p>
</div>

